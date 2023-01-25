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

  Future<String?> createPassword(
      String content, String name, String userOrMail, String url) async {
    isLoading = true;
    notifyListeners();
    final token = await storage.read(key: 'token') ?? '';
    final apiUrl = Uri.https(_baseUrl, '/password');
    final resp = await http.post(apiUrl,
        headers: {
          'Authorization': 'Bearer $token',
          'Content-Type': 'application/json',
        },
        body: jsonEncode({
          'content': content,
          'name': name,
          'userOrMail': userOrMail,
          'url': url,
        }));
    if (resp.statusCode != 200 && resp.statusCode != 201) {
      return 'Error';
    }
    final decodedBody = json.decode(resp.body);
    final password = Password.fromJson(decodedBody);
    passwords.add(password);
    isLoading = false;
    notifyListeners();
    return null;
  }

  Future<String?> deletePassword(String id) async {
    final token = await storage.read(key: 'token') ?? '';
    final apiUrl = Uri.https(_baseUrl, '/password/$id');
    final resp = await http.delete(apiUrl, headers: {
      'Authorization': 'Bearer $token',
      'Content-Type': 'application/json',
    });

    passwords.removeWhere((element) => '${element.id}' == id);
    notifyListeners();
    if (resp.statusCode != 200 && resp.statusCode != 201) {
      return 'Error';
    }
    return null;
  }
}
