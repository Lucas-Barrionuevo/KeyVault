import 'package:flutter/material.dart';

class ListTitle extends StatelessWidget {
  final String text;
  const ListTitle({
    Key? key,
    required this.text,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 35),
      child: Text(text,
          style: const TextStyle(
            fontWeight: FontWeight.bold,
            fontSize: 24,
          )),
    );
  }
}
