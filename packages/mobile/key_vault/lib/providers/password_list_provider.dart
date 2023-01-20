import 'package:flutter/material.dart';
import 'package:key_vault/models/password.dart';

class PasswordListProvider extends ChangeNotifier {
  String _search = '';
  String get search => _search;
  List<Password> passwords = [];
  List<Password> _auxPasswords = [];

  void setPasswords(List<Password> value) {
    if (passwords.isEmpty) {
      _auxPasswords = value;
      passwords = value;
    }
  }

  set search(String value) {
    _search = value;
    if (value.isEmpty) {
      passwords = _auxPasswords;
    } else {
      passwords = _auxPasswords
          .where((element) =>
              element.name.toLowerCase().contains(value.toLowerCase()))
          .toList();
    }
    notifyListeners();
  }
}
