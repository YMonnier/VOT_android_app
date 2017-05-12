# VO'T Android App

Git Repo containning the Android Application.

## Coding Rules
 * Follow the below architecture

`feature package` contains all activities and `utilites package`contains util/helper class like `Gson Singleton`, `Email Validator` and so on...
```
├── features
│   ├── feature_package_name_1
│   ├── feature_package_name_2
└── utilities
    ├── loader
    └── validator
```

 * Use explicit name package
 * Create a `string.xml`for each activities. (ie: `res/values/strings_login_activity.xml`) which will contain the constants (input error, view, ...)
 * Use `Android Annotation` + `Spring Rest Annotation`
