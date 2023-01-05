class Icon {
  Icon({
    required this.id,
    required this.domain,
    required this.url,
  });

  int id;
  String domain;
  String url;

  factory Icon.fromJson(Map<String, dynamic> json) => Icon(
        id: json["id"],
        domain: json["domain"],
        url: json["url"],
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "domain": domain,
        "url": url,
      };
}
