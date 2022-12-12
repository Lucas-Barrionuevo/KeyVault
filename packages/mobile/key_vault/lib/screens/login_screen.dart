import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:key_vault/ui/input_decorations.dart';

class LoginScreen extends StatelessWidget {
  const LoginScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: SafeArea(
      child: Column(
        children: [
          const _Logo(),
          const _Title(),
          const _Subtitle(),
          const SizedBox(
            height: 40,
          ),
          const _LoginForm(),
          const _ForgotMyPasswordButton(),
          Expanded(
            child: Container(),
          ),
          const _RegisterButton()
        ],
      ),
    ));
  }
}

class _RegisterButton extends StatelessWidget {
  const _RegisterButton({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return RichText(
      text: TextSpan(
          style: const TextStyle(color: Colors.black54, fontSize: 17),
          children: [
            const TextSpan(
              text: "¿No tienes una cuenta?",
            ),
            const WidgetSpan(child: SizedBox(width: 5)),
            TextSpan(
              recognizer: TapGestureRecognizer()
                ..onTap = () => print('Tap Here onTap'),
              text: "Registrate",
              style: const TextStyle(color: Color(0xffF0C029)),
            )
          ]),
    );
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
          style: TextStyle(color: Color(0xffF0C029)),
        ));
  }
}

class _LoginForm extends StatefulWidget {
  const _LoginForm({
    Key? key,
  }) : super(key: key);

  @override
  State<_LoginForm> createState() => _LoginFormState();
}

class _LoginFormState extends State<_LoginForm> {
  bool passwordVisible = true;
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
          TextFormField(
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
                    icon: const Icon(
                      Icons.remove_red_eye_outlined,
                      color: Colors.black54,
                    ))),
          ),
          const SizedBox(
            height: 20,
          ),
          MaterialButton(
            onPressed: () {},
            color: const Color(0xffF0C029),
            shape:
                RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
            child: Container(
              width: double.infinity,
              alignment: Alignment.center,
              padding: const EdgeInsets.symmetric(vertical: 20),
              child: const Text(
                "Iniciar sesión",
                style: TextStyle(color: Colors.white, fontSize: 18),
              ),
            ),
          ),
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

class _Logo extends StatelessWidget {
  const _Logo({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Center(
      // ignore: todo
      //TODO: replace for logo
      child: Container(
        margin: const EdgeInsets.only(top: 70, bottom: 40),
        height: 100,
        width: 100,
        color: Colors.red,
      ),
    );
  }
}
