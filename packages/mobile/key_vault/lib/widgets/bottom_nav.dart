import 'package:flutter/material.dart';
import 'package:key_vault/providers/providers.dart';
import 'package:key_vault/theme/app_theme.dart';
import 'package:provider/provider.dart';

class BottomNav extends StatelessWidget {
  const BottomNav({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final bottomNavProvider = Provider.of<BottomNavProvider>(context);
    final currentIndex = bottomNavProvider.selectedMenuOpt;
    return BottomNavigationBar(
        backgroundColor: Colors.white,
        showSelectedLabels: false,
        currentIndex: currentIndex,
        showUnselectedLabels: false,
        selectedItemColor: AppTheme.primary,
        onTap: (value) => bottomNavProvider.selectedMenuOpt = value,
        iconSize: 28,
        items: const [
          BottomNavigationBarItem(icon: Icon(Icons.home), label: 'Home'),
          BottomNavigationBarItem(icon: Icon(Icons.qr_code), label: 'QR'),
          BottomNavigationBarItem(icon: Icon(Icons.key), label: 'Passwords')
        ]);
  }
}
