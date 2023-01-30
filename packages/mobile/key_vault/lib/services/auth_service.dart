import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:key_vault/models/models.dart';
import 'package:key_vault/utils/services.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class AuthService extends ChangeNotifier {
  final String _baseUrl = "api-dev.keyvault.me";
  User? user;
  final storage = const FlutterSecureStorage();
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
    if (decodedResp["token"] != null) {
      await storage.write(key: 'token', value: decodedResp['token']);
    }
    user = User.fromJson(decodedResp);
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
    if (!ServicesUtils.isOk(resp)) {
      isLoading = false;
      notifyListeners();
      return "Error al iniciar sesión";
    }
    final Map<String, dynamic> decodedResp = json.decode(resp.body);
    user = User.fromJson(decodedResp);
    if (decodedResp["token"] != null) {
      await storage.write(key: 'token', value: decodedResp['token']);
    }
    isLoading = false;
    notifyListeners();
    return null;
  }

  Future logout() async {
    await storage.delete(key: 'token');
    user = null;
    return;
  }

  Future<bool> loginWithToken() async {
    final token = await readToken();
    if (token == "") return false;
    final apiUrl = Uri.https(_baseUrl, '/user/me');
    final resp = await http.get(
      apiUrl,
      headers: {
        'Authorization': 'Bearer $token',
      },
    );
    if (!ServicesUtils.isOk(resp)) return false;
    final Map<String, dynamic> decodedResp = json.decode(resp.body);
    user = User.fromJson(decodedResp);
    if (decodedResp["token"] != null) {
      await storage.write(key: 'token', value: decodedResp['token']);
    }
    return true;
  }

  Future<String> readToken() async {
    return await storage.read(key: 'token') ?? '';
  }

  Future<bool> deleteAccount() async {
    final token = await readToken();
    if (token == "") return false;
    final apiUrl = Uri.https(_baseUrl, '/user/${user?.id}');
    final resp = await http.delete(
      apiUrl,
      headers: {
        'Authorization': 'Bearer $token',
      },
    );
    if (!ServicesUtils.isOk(resp)) return false;
    await logout();
    return true;
  }
}
