import 'package:flutter/material.dart';
import 'package:key_vault/providers/auth_provider.dart';
import 'package:key_vault/providers/providers.dart';
import 'package:key_vault/services/auth_service.dart';
import 'package:key_vault/theme/app_theme.dart';
import 'package:key_vault/ui/input_decorations.dart';
import 'package:key_vault/utils/sizes.dart';
import 'package:key_vault/widgets/widgets.dart';
import 'package:provider/provider.dart';

class SettingsScreen extends StatelessWidget {
  const SettingsScreen({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    Sizes(context);
    return Scaffold(
      body: Column(
        children: const [_Header(), Expanded(child: _SettingsForm())],
      ),
    );
  }
}

class _SettingsForm extends StatelessWidget {
  const _SettingsForm({
    Key? key,
  }) : super(key: key);
  @override
  Widget build(BuildContext context) {
    final settingsForm = Provider.of<SettingsFormProvider>(context);
    final userProvider = Provider.of<AuthService>(context);

    return Padding(
      padding: EdgeInsets.symmetric(horizontal: Sizes.scaleHorizontal * 5),
      child: Form(
        autovalidateMode: AutovalidateMode.onUserInteraction,
        child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Padding(
                padding: EdgeInsets.only(top: Sizes.scaleVertical * 8),
                child: TextFormField(
                    initialValue: userProvider.user != null
                        ? userProvider.user?.email
                        : "",
                    autocorrect: false,
                    enabled: false,
                    cursorColor: AppTheme.primary,
                    keyboardType: TextInputType.emailAddress,
                    onChanged: (value) => settingsForm.email = value,
                    decoration:
                        InputDecorations.formDecoration(label: "Email")),
              ),
              Padding(
                padding: EdgeInsets.only(bottom: Sizes.scaleVertical * 8),
                child: Column(
                  children: [
                    TextButton(
                      onPressed: () {
                        final authService =
                            Provider.of<AuthService>(context, listen: false);
                        authService.logout();
                        Navigator.pushReplacementNamed(context, 'login');
                      },
                      child: const Text("Cerrar sesión"),
                    ),
                    SizedBox(height: Sizes.scaleVertical),
                    TextButton(
                      onPressed: () {},
                      child: const Text("Eliminar cuenta"),
                    )
                  ],
                ),
              )
            ]),
      ),
    );
  }
}

class _Header extends StatelessWidget {
  const _Header({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Stack(
      alignment: Alignment.center,
      clipBehavior: Clip.none,
      children: [
        Container(
          decoration: BoxDecoration(
            color: const Color(0xffFEF9EA),
            borderRadius: BorderRadius.circular(40),
          ),
          height: Sizes.scaleHorizontal * 50,
          width: double.infinity,
        ),
        Positioned(
          bottom: -Sizes.scaleVertical * 5,
          child: CircleAvatar(
            radius: Sizes.scaleVertical * 5,
            child: Icon(Icons.abc, size: Sizes.scaleVertical * 8),
          ),
        )
      ],
    );
  }
}
