import 'package:flutter/material.dart';
import 'package:key_vault/theme/app_theme.dart';
import 'package:key_vault/ui/input_decorations.dart';
import 'package:key_vault/utils/sizes.dart';

class PasswordsScreen extends StatelessWidget {
  const PasswordsScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Sizes(context);
    return Scaffold(
      backgroundColor: Colors.white,
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.of(context).pushNamed('add_password_screen');
        },
        backgroundColor: AppTheme.primary,
        child: const Icon(Icons.add),
      ),
      appBar: AppBar(
          title: const Text("Password"),
          backgroundColor: Colors.white,
          centerTitle: true,
          elevation: 0,
          toolbarHeight: Sizes.scaleVertical * 7.5,
          titleTextStyle: const TextStyle(
              color: Colors.black, fontSize: 25, fontWeight: FontWeight.bold)),
      body: Column(
        children: const [
          _SearchBar(),
        ],
      ),
    );
  }
}

class _SearchBar extends StatelessWidget {
  const _SearchBar({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.symmetric(horizontal: Sizes.scaleHorizontal * 5),
      child: TextFormField(
        style: const TextStyle(
            color: Colors.black, fontWeight: FontWeight.bold, fontSize: 18),
        decoration: InputDecorations.searchDecoration(),
      ),
    );
  }
}
