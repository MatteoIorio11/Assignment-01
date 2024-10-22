package it.unibo.sap.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "sap.ass01.exagonal")
public class ArchitecturalTest {
    private static final String BUSINESS_LAYER_PACKAGE = "sap.ass01.exagonal.business..";
    private static final String PERSISTENCE_LAYER_PACKAGE = "sap.ass01.exagonal.persistence..";
    private static final String PRESENTATION_LAYER_PACKAGE = "sap.ass01.exagonal.presentation..";
    private static final String SERVICE_LAYER_PACKAGE = "sap.ass01.exagonal.service..";

    JavaClasses importedClasses = new ClassFileImporter().importPackages("sap.ass01.exagonal");

    @ArchTest
    public static final ArchRule business_layer_should_not_depend_on_persistence =
            noClasses()
                    .that()
                    .resideInAPackage(BUSINESS_LAYER_PACKAGE)
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage(PERSISTENCE_LAYER_PACKAGE);
    @ArchTest
    public static final ArchRule business_layer_should_not_depend_on_presentation =
            noClasses()
                    .that()
                    .resideInAPackage(BUSINESS_LAYER_PACKAGE)
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage(PRESENTATION_LAYER_PACKAGE)
                    .allowEmptyShould(true);
    @ArchTest
    public static final ArchRule business_layer_should_not_depend_on_services =
            noClasses()
                    .that()
                    .resideInAPackage(BUSINESS_LAYER_PACKAGE)
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage(SERVICE_LAYER_PACKAGE)
                    .allowEmptyShould(true);
}
