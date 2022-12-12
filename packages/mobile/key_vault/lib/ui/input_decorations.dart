import 'package:flutter/material.dart';

class InputDecorations {
  static InputDecoration authInputDecoration(
      {required String labelText, Widget? suffixIcon}) {
    return InputDecoration(labelText: labelText, suffixIcon: suffixIcon);
  }
}
