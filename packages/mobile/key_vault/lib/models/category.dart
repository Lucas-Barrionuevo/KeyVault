class Category {
  Category({
    this.id,
    required this.name,
    this.preview,
  });

  int? id;
  String name;
  String? preview;

  factory Category.fromJson(Map<String, dynamic> json) => Category(
        id: json["id"],
        name: json["name"],
        preview: json["preview"],
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "name": name,
        "preview": preview,
      };
}
