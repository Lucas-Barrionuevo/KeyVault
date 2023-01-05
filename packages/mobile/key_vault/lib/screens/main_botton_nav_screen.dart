import 'package:flutter/material.dart';
import 'package:key_vault/providers/bottom_nav_provider.dart';
import 'package:key_vault/screens/screens.dart';
import 'package:key_vault/widgets/widgets.dart';
import 'package:provider/provider.dart';

class MainBottomNavScreen extends StatelessWidget {
  const MainBottomNavScreen({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    final bottomNavProvider = Provider.of<BottomNavProvider>(context);
    final index = bottomNavProvider.selectedMenuOpt;
    final screens = {
      0: const HomeScreen(),
      1: Container(),
      2: const PasswordsScreen(),
      3: const SettingsScreen()
    };
    return Scaffold(
      bottomNavigationBar: const BottomNav(),
      body: screens[index],
    );
  }
}
