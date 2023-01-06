import 'package:flutter/material.dart';
import 'package:key_vault/providers/password_form_provider.dart';
import 'package:key_vault/providers/providers.dart';
import 'package:key_vault/screens/screens.dart';
import 'package:key_vault/theme/app_theme.dart';
import 'package:provider/provider.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => BottomNavProvider()),
        ChangeNotifierProvider(create: (_) => PasswordFormProvider()),
        ChangeNotifierProvider(create: (_) => SettingsFormProvider()),
      ],
      child: MaterialApp(
        routes: {
          'login': (context) => const LoginScreen(),
          'register': (context) => const RegisterScreen(),
          'main_bottom_nav_screen': (context) => const MainBottomNavScreen(),
          'add_password_screen': (context) => const AddPasswordScreen(),
        },
        initialRoute: 'main_bottom_nav_screen',
        debugShowCheckedModeBanner: false,
        theme: AppTheme.lighTheme,
      ),
    );
  }
}
