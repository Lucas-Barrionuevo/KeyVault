import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:key_vault/models/models.dart';
import 'package:key_vault/utils/services.dart';

class AuthService extends ChangeNotifier {
  final String _baseUrl = "api-dev.keyvault.me";
  late final User user;
  bool isLoading = false;

  Future<String?> register(String email, String password) async {
    isLoading = true;
    notifyListeners();
    final Map<String, dynamic> body = {'email': email, 'password': password};
    final url = Uri.https(_baseUrl, '/user/register');
    final resp = await http.post(
      url,
      body: json.encode(body),
      headers: {"Content-Type": "application/json"},
    );
    final Map<String, dynamic> decodedResp = json.decode(resp.body);
    user = decodedResp as User;
    isLoading = false;
    notifyListeners();
    return null;
  }

  Future<String?> login(String email, String password) async {
    isLoading = true;
    notifyListeners();
    final Map<String, dynamic> body = {'email': email, 'password': password};
    final url = Uri.https(_baseUrl, '/user/login');
    final resp = await http.post(
      url,
      body: json.encode(body),
      headers: {"Content-Type": "application/json"},
    );
    //TODO: error handle
    print(resp.statusCode);
    if (!ServicesUtils.isOk(resp)) {
      isLoading = false;
      notifyListeners();
      return "Error al iniciar sesión";
    }
    final Map<String, dynamic> decodedResp = json.decode(resp.body);
    user = User.fromJson(decodedResp);
    print(user);
    isLoading = false;
    notifyListeners();
    return null;
  }
}
