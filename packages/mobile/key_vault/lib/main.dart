import 'package:flutter/material.dart';
import 'package:key_vault/screens/screens.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      routes: {'login': (context) => const LoginScreen()},
      initialRoute: 'login',
      debugShowCheckedModeBanner: false,
    );
  }
}