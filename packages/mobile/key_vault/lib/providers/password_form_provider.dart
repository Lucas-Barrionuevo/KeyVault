import 'package:flutter/material.dart';
import 'package:key_vault/models/models.dart';
import 'package:key_vault/services/services.dart';
import 'package:provider/provider.dart';

class PasswordFormProvider extends ChangeNotifier {
  GlobalKey<FormState> formKey = GlobalKey<FormState>();

  String name = '';
  String username = '';
  String url = '';
  String category = '';
  String password = '';
  bool _isLoading = false;

  bool get isLoading => _isLoading;
  set isLoading(bool value) {
    _isLoading = value;
    notifyListeners();
  }

  bool isValidForm() {
    return formKey.currentState?.validate() ?? false;
  }

  Future<Password> createPassword(BuildContext context) async {
    return Provider.of<PasswordService>(context, listen: false)
        .createPassword(password, name, username, url);
  }
}
