import 'package:flutter/material.dart';
import 'package:key_vault/theme/app_theme.dart';

class AuthSubmitButton extends StatelessWidget {
  const AuthSubmitButton({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialButton(
      onPressed: () {},
      highlightColor: AppTheme.primary,
      highlightElevation: 1,
      elevation: 0,
      color: AppTheme.primary,
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
      child: Container(
        width: double.infinity,
        alignment: Alignment.center,
        padding: const EdgeInsets.symmetric(vertical: 20),
        child: const Text(
          "Iniciar sesión",
          style: TextStyle(color: Colors.white, fontSize: 18),
        ),
      ),
    );
  }
}
