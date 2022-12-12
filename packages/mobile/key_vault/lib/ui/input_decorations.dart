import 'package:flutter/material.dart';

class InputDecorations {
  static InputDecoration authInputDecoration(
      {required String labelText, Widget? suffixIcon}) {
    return InputDecoration(
        labelText: labelText,
        filled: true,
        suffixIcon: suffixIcon,
        fillColor: Colors.grey[200],
        border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(10),
            borderSide: BorderSide.none),
        labelStyle: const TextStyle(color: Colors.black54));
  }
}
