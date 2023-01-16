import 'package:flutter/material.dart';

import 'package:key_vault/providers/providers.dart';
import 'package:key_vault/services/auth_service.dart';
import 'package:key_vault/theme/app_theme.dart';
import 'package:key_vault/ui/input_decorations.dart';
import 'package:key_vault/widgets/widgets.dart';
import 'package:provider/provider.dart';

class LoginScreen extends StatelessWidget {
  const LoginScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: SafeArea(
      child: SingleChildScrollView(
        child: Expanded(
          child: Column(
            children: [
              const AuthLogo(),
              const _Title(),
              const _Subtitle(),
              const SizedBox(
                height: 40,
              ),
              ChangeNotifierProvider(
                  create: (_) => LoginFormProvider(),
                  child: const _LoginForm()),
              const SizedBox(
                height: 25,
              ),
              const _ForgotMyPasswordButton(),
              AuthTextAndButton(
                onTap: () {},
                text1: '¿No tienes una cuenta?',
                text2: 'Registrate',
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

class _LoginForm extends StatelessWidget {
  const _LoginForm();
  @override
  Widget build(BuildContext context) {
    final loginForm = Provider.of<LoginFormProvider>(context);
    final authProvider = Provider.of<AuthService>(context);
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 20),
      key: loginForm.formKey,
      child: Form(
          child: Column(
        children: [
          TextFormField(
            keyboardType: TextInputType.emailAddress,
            cursorColor: AppTheme.primary,
            autocorrect: false,
            onChanged: (value) => loginForm.email = value,
            decoration:
                InputDecorations.authInputDecoration(labelText: 'Email'),
          ),
          const SizedBox(
            height: 25,
          ),
          AuthPasswordInput(onChanged: (value) => loginForm.password = value),
          const SizedBox(
            height: 25,
          ),
          SubmitButton(
            title: "Iniciar sesión",
            isLoading: authProvider.isLoading,
            onPressed: authProvider.isLoading
                ? null
                : () async {
                    final authService =
                        Provider.of<AuthService>(context, listen: false);
                    //TODO: error manage
                    await authService.login(
                        loginForm.email, loginForm.password);
                  },
          )
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
