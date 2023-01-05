import 'package:flutter/material.dart';
import 'package:key_vault/theme/app_theme.dart';
import 'package:key_vault/ui/input_decorations.dart';

class PasswordInput extends StatefulWidget {
  const PasswordInput({super.key});

  @override
  State<PasswordInput> createState() => _PasswordInputState();
}

class _PasswordInputState extends State<PasswordInput> {
  bool passwordVisible = true;
  @override
  Widget build(BuildContext context) {
    return TextFormField(
      keyboardType: TextInputType.none,
      obscureText: passwordVisible,
      cursorColor: AppTheme.primary,
      decoration: InputDecorations.formDecoration(
          label: 'Contraseña',
          suffixIcon: IconButton(
              splashColor: Colors.transparent,
              highlightColor: Colors.transparent,
              onPressed: (() {
                setState(() {
                  passwordVisible = !passwordVisible;
                });
              }),
              icon: Icon(
                !passwordVisible ? Icons.visibility : Icons.visibility_off,
                color: Colors.black54,
              ))),
    );
  }
}
