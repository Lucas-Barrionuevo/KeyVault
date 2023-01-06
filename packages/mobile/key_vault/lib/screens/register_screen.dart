import 'package:flutter/material.dart';
import 'package:key_vault/theme/app_theme.dart';
import 'package:key_vault/ui/input_decorations.dart';
import 'package:key_vault/widgets/widgets.dart';

class RegisterScreen extends StatelessWidget {
  const RegisterScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: SafeArea(
      child: SingleChildScrollView(
        child: Expanded(
          child: Column(
            children: [
              const AuthLogo(),
              const AuthTitle(
                title: "Registrate en Key Vault",
              ),
              const AuthSubtitle(),
              const SizedBox(
                height: 40,
              ),
              const _RegisterForm(),
              const SizedBox(
                height: 25,
              ),
              const _ForgotMyPasswordButton(),
              AuthTextAndButton(
                onTap: () {
                  Navigator.of(context).pushReplacementNamed('login');
                },
                text1: '¿Ya tienes una cuenta?',
                text2: 'Inicia sesión',
              )
            ],
          ),
        ),
      ),
    ));
  }
}

class _ForgotMyPasswordButton extends StatelessWidget {
  const _ForgotMyPasswordButton({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return TextButton(
        onPressed: () {},
        child: const Text(
          "Olvidé mi contraseña",
          style: TextStyle(color: AppTheme.primary),
        ));
  }
}

class _RegisterForm extends StatelessWidget {
  const _RegisterForm();

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 20),
      child: Form(
          child: Column(
        children: [
          TextFormField(
            keyboardType: TextInputType.emailAddress,
            cursorColor: AppTheme.primary,
            decoration:
                InputDecorations.authInputDecoration(labelText: 'Email'),
          ),
          const SizedBox(
            height: 25,
          ),
          const AuthPasswordInput(),
          const SizedBox(
            height: 25,
          ),
          const SubmitButton(
            title: "Registrate",
          )
        ],
      )),
    );
  }
}
