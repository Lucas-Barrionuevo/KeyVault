import 'package:flutter/material.dart';
import 'package:key_vault/ui/input_decorations.dart';

class AuthPasswordInput extends StatefulWidget {
  const AuthPasswordInput({super.key});

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
      cursorColor: const Color(0xffF0C029),
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
