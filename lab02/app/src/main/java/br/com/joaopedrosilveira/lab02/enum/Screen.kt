package br.com.joaopedrosilveira.lab02.enum

enum class Screen(val value: String) {
    SUM("Lista 1 - Exercício 1"),
    INPUT_TEXT("Lista 1 - Exercício 2"),
    ACCELEROMETER("Lista 1 - Exercício 3"),
    SENSOR("Lista 2 - Exercícios 1, 2 e 3");

    companion object {
        fun fromName(name: String): Screen? {
            for (value in enumValues<Screen>()) {
                if (value.name == name) {
                    return value;
                }
            }

            return null;
        }
    }
}