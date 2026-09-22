# CSS

To fix box-sizing, use this:

```css
html {
  box-sizing: border-box;
}

*,
*::before,
*::after {
  box-sizing: inherit;
}
```

Reasoning:

Flat universal selector:

```css
*,
*::before,
*::after {
  box-sizing: border-box;
}
```

This hits every element directly. If a third-party widget sets .widget { box-sizing: content-box }, that applies to .widget itself but your * rule still forces border-box on all its children. The override doesn't cascade.

Inherit version:

```css
html {
  box-sizing: border-box;
}

*,
*::before,
*::after {
  box-sizing: inherit;
}
```

Every element takes its value from its parent. So .widget { box-sizing: content-box } now cascades down to everything inside it, because the children are inheriting rather than being set directly.

Same result in a codebase you fully control. The inherit version only matters if you're embedding components whose internal box model you don't own.
