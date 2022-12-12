import 'package:flutter/material.dart';

class AppTheme {
  static const Color primary = Color(0xffF0C029);
  static final ThemeData lighTheme = ThemeData.light().copyWith(
      primaryColor: primary,
      textButtonTheme: TextButtonThemeData(
        style: TextButton.styleFrom(
            backgroundColor: Colors.transparent, foregroundColor: primary),
      ),
      inputDecorationTheme: InputDecorationTheme(
          fillColor: Colors.grey[200],
          filled: true,
          border: OutlineInputBorder(
              borderRadius: BorderRadius.circular(10),
              borderSide: BorderSide.none),
          labelStyle: const TextStyle(color: Colors.black54)));
}
