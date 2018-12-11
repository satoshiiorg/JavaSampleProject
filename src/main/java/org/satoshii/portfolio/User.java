package org.satoshii.portfolio;

import com.google.auto.value.AutoValue;

// Lombokで進める これはサンプルとして残しておく
@AutoValue
public abstract class User {
    public abstract int id();
    public abstract String name();
    // ここは手動なりスニペットなりで生成が必要
    public static User create(int id, String name) {
        return new AutoValue_User(id, name);
    }
}
