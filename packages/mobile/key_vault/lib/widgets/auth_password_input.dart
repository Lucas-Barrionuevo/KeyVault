import 'package:flutter/material.dart';
import 'package:key_vault/theme/app_theme.dart';
import 'package:key_vault/ui/input_decorations.dart';

class AuthPasswordInput extends StatefulWidget {
  final void Function(String) onChanged;
  const AuthPasswordInput({super.key, required this.onChanged});

  @override
  State<AuthPasswordInput> createState() => _AuthPasswordInputState();
}

class _AuthPasswordInputState extends State<AuthPasswordInput> {
  bool passwordVisible = true;
  @override
  Widget build(BuildContext context) {
    return TextFormField(
      keyboardType: TextInputType.none,
      obscureText: passwordVisible,
      cursorColor: AppTheme.primary,
      onChanged: widget.onChanged,
      decoration: InputDecorations.authInputDecoration(
          labelText: 'Contraseña',
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
