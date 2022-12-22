import 'package:flutter/material.dart';
import 'package:key_vault/screens/passwords_screen.dart';
import 'package:key_vault/screens/screens.dart';
import 'package:key_vault/theme/app_theme.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      routes: {
        'login': (context) => const LoginScreen(),
        'home_screen': (context) => const HomeScreen(),
        'passwords_screen': (context) => const PasswordsScreen(),
      },
      initialRoute: 'passwords_screen',
      debugShowCheckedModeBanner: false,
      theme: AppTheme.lighTheme,
    );
  }
}
