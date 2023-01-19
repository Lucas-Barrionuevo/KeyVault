import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:key_vault/models/models.dart';
import 'package:http/http.dart' as http;

class PasswordService extends ChangeNotifier {
  bool isLoading = false;
  final String _baseUrl = "api-dev.keyvault.me";
  List<Password> passwords = [];
  final storage = const FlutterSecureStorage();

  PasswordService() {
    loadPasswords();
    print("Le pegué una vez al backend");
  }

  Future<List<Password>> loadPasswords() async {
    isLoading = true;
    notifyListeners();
    final token = await storage.read(key: 'token') ?? '';
    final url = Uri.https(_baseUrl, '/password', {
      'Content-Type': 'application/json',
    });
    final resp = await http.get(url, headers: {
      'Authorization': 'Bearer $token',
    });
    final List<dynamic> decodedBody = json.decode(resp.body);
    //Cast to List<Password>
    passwords = decodedBody.map((e) => Password.fromJson(e)).toList();
    isLoading = false;
    notifyListeners();
    return passwords;
    // passwords.forEach((key, value) {
    //   final tempPw = Password.fromJson(value);
    //   passwords.add(tempPw);
    // });

    // isLoading = false;
    // notifyListeners();
    // return passwords;
  }
}
