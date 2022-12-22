import 'package:flutter/material.dart';

class InputDecorations {
  static InputDecoration authInputDecoration(
      {required String labelText, Widget? suffixIcon}) {
    return InputDecoration(labelText: labelText, suffixIcon: suffixIcon);
  }

  static InputDecoration searchDecoration() {
    return InputDecoration(
        enabledBorder: OutlineInputBorder(
          // width: 0.0 produces a thin "hairline" border
          borderSide: const BorderSide(color: Color(0xffF6F8F9), width: 0.0),
          borderRadius: BorderRadius.circular(20),
        ),
        filled: true,
        prefixIcon: const Icon(
          Icons.search,
          color: Color(0xffA6AFB3),
        ),
        hintStyle: const TextStyle(
            color: Color(0xffA6AFB3),
            fontWeight: FontWeight.bold,
            fontSize: 18),
        hintText: "Search",
        fillColor: const Color(0xffF6F8F9));
  }
}
