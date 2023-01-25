import 'dart:io' show Platform;

import 'package:flutter/material.dart';
import 'package:key_vault/providers/password_form_provider.dart';
import 'package:key_vault/theme/app_theme.dart';
import 'package:key_vault/ui/input_decorations.dart';
import 'package:key_vault/utils/sizes.dart';
import 'package:key_vault/validations/password_validations.dart';
import 'package:provider/provider.dart';

class AddPasswordScreen extends StatelessWidget {
  const AddPasswordScreen({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    final appbarHeight = Platform.isAndroid
        ? Size.fromHeight(Sizes.scaleVertical * 12)
        : Size.fromHeight(Sizes.scaleVertical * 8);
    final toolbarHeight =
        Platform.isAndroid ? Sizes.scaleVertical * 12 : Sizes.scaleVertical * 8;
    final passwordForm = Provider.of<PasswordFormProvider>(context);

    goBack() {
      Navigator.pop(context);
    }

    Sizes(context);
    return Scaffold(
      backgroundColor: Colors.white,
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
      resizeToAvoidBottomInset: false,
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          final isValid = passwordForm.isValidForm();
          if (isValid) {
            final error = await passwordForm.createPassword(context);
            if (error == null) goBack();
            //TODO: SNACKBAR
          }
        },
        backgroundColor: AppTheme.primary,
        child: const Icon(Icons.save),
      ),
      appBar: PreferredSize(
          preferredSize: appbarHeight,
          child: AppBar(
              title: const Text("Agregar Contraseña"),
              backgroundColor: Colors.white,
              iconTheme: const IconThemeData(
                color: Colors.black, //change your color here
              ),
              centerTitle: true,
              elevation: 0,
              toolbarHeight: toolbarHeight,
              titleTextStyle: const TextStyle(
                  color: Colors.black,
                  fontSize: 25,
                  fontWeight: FontWeight.bold))),
      body: SingleChildScrollView(
        child: Padding(
          padding: EdgeInsets.symmetric(
              horizontal: Sizes.scaleHorizontal * 5,
              vertical: Sizes.scaleVertical),
          child: _PasswordForm(passwordForm: passwordForm),
        ),
      ),
    );
  }
}

class _PasswordForm extends StatelessWidget {
  const _PasswordForm({
    Key? key,
    required this.passwordForm,
  }) : super(key: key);

  final PasswordFormProvider passwordForm;

  @override
  Widget build(BuildContext context) {
    final String? initialUrl =
        ModalRoute.of(context)?.settings?.arguments as String?;
    return Form(
      key: passwordForm.formKey,
      autovalidateMode: AutovalidateMode.onUserInteraction,
      child: Column(children: [
        TextFormField(
            autocorrect: false,
            cursorColor: AppTheme.primary,
            onChanged: (value) => passwordForm.name = value,
            validator: (value) => PasswordValidations.name(value),
            decoration: InputDecorations.formDecoration(
                hintText: "Facebook", label: "Nombre")),
        SizedBox(height: Sizes.scaleVertical * 3),
        // AutocompleteInput(
        //   label: "Categoría",
        //   hintText: "Trabajo",
        //   onSelected: (String selected) {
        //     passwordForm.category = selected;
        //   },
        // ),
        // SizedBox(height: Sizes.scaleVertical * 3),
        TextFormField(
            autocorrect: false,
            cursorColor: AppTheme.primary,
            onChanged: (value) => passwordForm.username = value,
            validator: (value) => PasswordValidations.username(value),
            decoration: InputDecorations.formDecoration(
                hintText: "@agus.dev11", label: "Nombre de usuario o Email")),
        SizedBox(height: Sizes.scaleVertical * 3),
        TextFormField(
            autocorrect: false,
            cursorColor: AppTheme.primary,
            onChanged: (value) => passwordForm.url = value,
            initialValue: initialUrl,
            validator: (value) => PasswordValidations.url(value),
            decoration: InputDecorations.formDecoration(
                hintText: "https://facebook.com/", label: "URL")),
        SizedBox(height: Sizes.scaleVertical * 3),
        TextFormField(
            autocorrect: false,
            validator: (value) => PasswordValidations.password(value),
            onChanged: (value) => passwordForm.password = value,
            cursorColor: AppTheme.primary,
            decoration: InputDecorations.formDecoration(
                hintText: "1234", label: "Contraseña")),
        SizedBox(height: Sizes.scaleVertical * 3),
      ]),
    );
  }
}
