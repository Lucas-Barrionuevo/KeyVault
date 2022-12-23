import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:key_vault/theme/app_theme.dart';
import 'package:key_vault/ui/input_decorations.dart';

import 'package:key_vault/utils/sizes.dart';

class AutocompleteInput extends StatelessWidget {
  final void Function(String)? onSelected;
  // final List<String> suggestions;
  final String hintText;
  final String label;
  const AutocompleteInput(
      {super.key,
      required this.onSelected,
      required this.hintText,
      required this.label});

  @override
  Widget build(BuildContext context) {
    const suggestions = ['ha', 'be', 'bi', 'bu', 'br', 'Trabajo'];
    Sizes(context);
    return Autocomplete(
        optionsBuilder: (TextEditingValue textEditingValue) {
          if (textEditingValue.text == '') {
            return const Iterable<String>.empty();
          } else {
            List<String> matches = <String>[];
            matches.addAll(suggestions);

            matches.retainWhere((s) {
              return s
                  .toLowerCase()
                  .contains(textEditingValue.text.toLowerCase());
            });
            return matches;
          }
        },
        onSelected: onSelected,
        fieldViewBuilder: (BuildContext context,
            TextEditingController fieldTextEditingController,
            FocusNode fieldFocusNode,
            VoidCallback onFieldSubmitted) {
          return TextField(
            controller: fieldTextEditingController,
            focusNode: fieldFocusNode,
            cursorColor: AppTheme.primary,
            onChanged: onSelected,
            decoration: InputDecorations.formDecoration(
                hintText: hintText, label: label),
          );
        },
        optionsViewBuilder: (context, onSelected, options) {
          return Align(
            alignment: Alignment.topLeft,
            child: Material(
              elevation: 4.0,
              child: ConstrainedBox(
                constraints: BoxConstraints(
                    maxHeight: 200,
                    maxWidth: Sizes.screenWidth -
                        Sizes.scaleHorizontal *
                            10), //RELEVANT CHANGE: added maxWidth
                child: ListView.builder(
                  padding: EdgeInsets.zero,
                  shrinkWrap: true,
                  itemCount: options.length,
                  itemBuilder: (BuildContext context, int index) {
                    final String option = options.elementAt(index);
                    return InkWell(
                      onTap: () {
                        onSelected(option);
                      },
                      child: Builder(builder: (BuildContext context) {
                        final bool highlight =
                            AutocompleteHighlightedOption.of(context) == index;
                        if (highlight) {
                          SchedulerBinding.instance
                              .addPostFrameCallback((Duration timeStamp) {
                            Scrollable.ensureVisible(context, alignment: 0.5);
                          });
                        }
                        return Container(
                          padding: const EdgeInsets.all(16.0),
                          child: Text(option),
                        );
                      }),
                    );
                  },
                ),
              ),
            ),
          );
        });
  }
}
