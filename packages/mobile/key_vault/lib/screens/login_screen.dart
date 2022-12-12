import 'package:flutter/material.dart';
import 'package:key_vault/ui/input_decorations.dart';
import 'package:key_vault/widgets/widgets.dart';

class LoginScreen extends StatelessWidget {
  const LoginScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: SafeArea(
      child: Column(
        children: [
          const AuthLogo(),
          const _Title(),
          const _Subtitle(),
          const SizedBox(
            height: 40,
          ),
          const _LoginForm(),
          const SizedBox(
            height: 25,
          ),
          const _ForgotMyPasswordButton(),
          Expanded(
            child: Container(),
          ),
          AuthTextAndButton(
            onTap: () {},
            text1: '¿No tienes una cuenta?',
            text2: 'Registrate',
          )
        ],
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
        style: TextButton.styleFrom(
            backgroundColor: Colors.transparent,
            foregroundColor: const Color(0xffF0C029)),
        onPressed: () {},
        child: const Text(
          "Olvidé mi contraseña",
          style: TextStyle(color: Color(0xffF0C029)),
        ));
  }
}

class _LoginForm extends StatelessWidget {
  const _LoginForm();

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 20),
      child: Form(
          child: Column(
        children: [
          TextFormField(
            keyboardType: TextInputType.emailAddress,
            cursorColor: const Color(0xffF0C029),
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
          const AuthSubmitButton()
        ],
      )),
    );
  }
}

class _Subtitle extends StatelessWidget {
  const _Subtitle({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.only(top: 2),
      child: const Text(
        'Manten tus datos seguros',
        style: TextStyle(
            fontSize: 18, fontWeight: FontWeight.w300, color: Colors.black87),
      ),
    );
  }
}

class _Title extends StatelessWidget {
  const _Title({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(top: 20),
      child: Text('¡Bienvenido a Key Vault!',
          style: Theme.of(context)
              .textTheme
              .headlineSmall
              ?.copyWith(fontWeight: FontWeight.bold, color: Colors.black87)),
    );
  }
}
