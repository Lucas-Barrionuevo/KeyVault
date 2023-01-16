import 'package:flutter/material.dart';
import 'package:key_vault/models/models.dart';

class AuthProvider extends ChangeNotifier {
  bool _isLoading = false;
  late User _user;

  User? get user => _user;

  bool get isLoading => _isLoading;
  set isLoading(bool value) {
    _isLoading = value;
    notifyListeners();
  }
}
