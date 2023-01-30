import 'package:flutter/material.dart';

import 'package:key_vault/providers/providers.dart';
import 'package:key_vault/services/auth_service.dart';
import 'package:key_vault/theme/app_theme.dart';
import 'package:key_vault/ui/input_decorations.dart';
import 'package:key_vault/utils/sizes.dart';
import 'package:key_vault/widgets/widgets.dart';
import 'package:provider/provider.dart';
import 'package:animated_snack_bar/animated_snack_bar.dart';

class RegisterScreen extends StatelessWidget {
  const RegisterScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: SafeArea(
      child: SingleChildScrollView(
        physics: const BouncingScrollPhysics(),
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
              // const _ForgotMyPasswordButton(),
              AuthTextAndButton(
                onTap: () {
                  Navigator.pushReplacementNamed(context, 'login');
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

// ignore: unused_element
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
    void showSnackbar() {
      AnimatedSnackBar(
        mobileSnackBarPosition: MobileSnackBarPosition.top,
        snackBarStrategy: RemoveSnackBarStrategy(),
        builder: ((context) {
          return Container(
            height: Sizes.scaleVertical * 6,
            width: Sizes.scaleHorizontal * 50,
            decoration: const BoxDecoration(
                color: Color(0xffEEEEEE),
                borderRadius: BorderRadius.all(Radius.circular(20))),
            child: const Center(
                child: Text(
              'Error al Registrarte',
              style: TextStyle(color: Colors.black),
            )),
          );
        }),
      ).show(context);
    }

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
            title: "Registrarse",
            isLoading: authProvider.isLoading,
            onPressed: authProvider.isLoading
                ? null
                : () async {
                    final authService =
                        Provider.of<AuthService>(context, listen: false);
                    final bottomNavProvider =
                        Provider.of<BottomNavProvider>(context, listen: false);
                    //TODO: error manage
                    final errorMessage = await authService.register(
                        loginForm.email, loginForm.password);
                    if (errorMessage == null) {
                      // ignore: use_build_context_synchronously
                      Navigator.pushReplacementNamed(
                          context, 'main_bottom_nav_screen');
                      bottomNavProvider.selectedMenuOpt = 0;
                    } else {
                      showSnackbar();
                    }
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
        'Y manten tus datos seguros',
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
      child: Text('¡Registrate en Key Vault!',
          style: Theme.of(context)
              .textTheme
              .headlineSmall
              ?.copyWith(fontWeight: FontWeight.bold, color: Colors.black87)),
    );
  }
}
