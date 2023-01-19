import 'dart:convert';
import 'package:key_vault/models/models.dart';

Password passwordFromJson(String str) => Password.fromJson(json.decode(str));

String passwordToJson(Password data) => json.encode(data.toJson());

class Password {
  Password({
    required this.id,
    required this.content,
    required this.name,
    this.userOrMail,
    required this.createdAt,
    required this.seenQty,
    this.category,
    this.icon,
    this.url,
  });

  int id;
  String? content;
  String createdAt;
  int seenQty;
  String name;
  String? userOrMail;
  Category? category;
  Icon? icon;
  String? url;

  factory Password.fromJson(Map<String, dynamic> json) => Password(
        id: json["id"],
        content: json["content"],
        name: json["name"] ?? "No-Name",
        userOrMail: json["userOrMail"],
        createdAt: json["createdAt"],
        seenQty: json["seenqty"],
        category: json["category"] != null
            ? Category.fromJson(json["category"])
            : null,
        icon: json["icon"] != null ? Icon.fromJson(json["icon"]) : null,
        url: json["url"],
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "content": content,
        "name": name,
        "userOrMail": userOrMail,
        "createdAt": createdAt,
        "seenQty": seenQty,
        "category": category?.toJson(),
        "icon": icon?.toJson(),
        "url": url,
      };
}
