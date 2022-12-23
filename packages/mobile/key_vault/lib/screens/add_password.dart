import 'dart:io' show Platform;

import 'package:flutter/material.dart';
import 'package:key_vault/theme/app_theme.dart';
import 'package:key_vault/ui/input_decorations.dart';
import 'package:key_vault/utils/sizes.dart';
import 'package:key_vault/widgets/widgets.dart';

class AddPasswordScreen extends StatelessWidget {
  const AddPasswordScreen({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    final appbarHeight = Platform.isAndroid
        ? Size.fromHeight(Sizes.scaleVertical * 12)
        : Size.fromHeight(Sizes.scaleVertical * 8);
    final toolbarHeight =
        Platform.isAndroid ? Sizes.scaleVertical * 12 : Sizes.scaleVertical * 8;

    Sizes(context);
    return Scaffold(
      backgroundColor: Colors.white,
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
      resizeToAvoidBottomInset: false,
      floatingActionButton: FloatingActionButton(
        onPressed: () {},
        backgroundColor: AppTheme.primary,
        child: const Icon(Icons.save),
      ),
      appBar: PreferredSize(
          preferredSize: appbarHeight,
          child: AppBar(
              title: const Text("Add Password"),
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
          child: Form(
            child: Column(children: [
              TextFormField(
                  cursorColor: AppTheme.primary,
                  decoration: InputDecorations.formDecoration(
                      hintText: "Facebook", label: "Nombre")),
              SizedBox(height: Sizes.scaleVertical * 3),
              TextFormField(
                  cursorColor: AppTheme.primary,
                  decoration: InputDecorations.formDecoration(
                      hintText: "@agus.dev11",
                      label: "Nombre de usuario o Email")),
              SizedBox(height: Sizes.scaleVertical * 3),
              TextFormField(
                  cursorColor: AppTheme.primary,
                  decoration: InputDecorations.formDecoration(
                      hintText: "https://facebook.com/", label: "URL")),
              SizedBox(height: Sizes.scaleVertical * 3),
              TextFormField(
                  cursorColor: AppTheme.primary,
                  decoration: InputDecorations.formDecoration(
                      hintText: "1234", label: "Contraseña")),
              SizedBox(height: Sizes.scaleVertical * 3),
              AutocompleteInput(
                label: "Categoría",
                hintText: "Trabajo",
                onSelected: (String selected) {},
              ),
            ]),
          ),
        ),
      ),
    );
  }
}
