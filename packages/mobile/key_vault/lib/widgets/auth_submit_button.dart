import 'package:flutter/material.dart';

class AuthSubmitButton extends StatelessWidget {
  const AuthSubmitButton({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialButton(
      onPressed: () {},
      highlightColor: const Color(0xffF0C029),
      highlightElevation: 1,
      elevation: 0,
      color: const Color(0xffF0C029),
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
