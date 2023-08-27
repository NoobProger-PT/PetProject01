package ru.namelesscompany;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModuleMainTest {
    @Test
    void main() {
        Assertions.assertDoesNotThrow(ModuleMain::new);
        Assertions.assertDoesNotThrow(() -> ModuleMain.main(new String[]{}));
    }
}
