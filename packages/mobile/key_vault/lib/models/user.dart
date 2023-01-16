import 'dart:convert';

User? userFromJson(String str) => User.fromJson(json.decode(str));

String userToJson(User? data) => json.encode(data!.toJson());

class User {
  User({
    this.id,
    required this.email,
    required this.createdAt,
  });

  int? id;
  String email;
  String createdAt;

  factory User.fromJson(Map<String, dynamic> json) => User(
        id: json["id"],
        email: json["email"],
        createdAt: json["createdAt"],
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "email": email,
        "createdAt": createdAt,
      };
}
