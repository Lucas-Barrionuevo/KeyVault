import 'package:flutter/material.dart';
import 'package:key_vault/ui/input_decorations.dart';

class PasswordsScreen extends StatelessWidget {
  const PasswordsScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
          title: const Text("Password"),
          backgroundColor: Colors.white,
          centerTitle: true,
          elevation: 0,
          toolbarHeight: 70,
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
      padding: const EdgeInsets.symmetric(horizontal: 50),
      child: TextFormField(
        style: const TextStyle(
            color: Colors.black, fontWeight: FontWeight.bold, fontSize: 18),
        decoration: InputDecorations.searchDecoration(),
      ),
    );
  }
}
