import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:key_vault/models/models.dart';
import 'package:http/http.dart' as http;

class PasswordService extends ChangeNotifier {
  bool isLoading = false;
  final String _baseUrl = "api-dev.keyvault.me";
  List<Password> passwords = [];
  late Password selectedPassword;
  final storage = const FlutterSecureStorage();

  PasswordService() {
    loadPasswords();
  }

  Future<Password> loadPassword(String id) async {
    final token = await storage.read(key: 'token') ?? '';
    final url = Uri.https(_baseUrl, '/password/$id', {
      'Content-Type': 'application/json',
    });
    final resp = await http.get(url, headers: {
      'Authorization': 'Bearer $token',
    });
    final decodedBody = json.decode(resp.body);
    selectedPassword = Password.fromJson(decodedBody);
    notifyListeners();
    return selectedPassword;
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
    passwords = decodedBody.map((e) => Password.fromJson(e)).toList();
    isLoading = false;
    notifyListeners();
    return passwords;
  }
}
