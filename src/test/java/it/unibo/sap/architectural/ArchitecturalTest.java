package it.unibo.sap.architectural;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "sap.ass01.layered")
public class ArchitecturalTest {
    private static final String BUSINESS_LAYER_PACKAGE = "sap.ass01.layered.business..";
    private static final String PERSISTENCE_LAYER_PACKAGE = "sap.ass01.layered.persistence..";
    private static final String PRESENTATION_LAYER_PACKAGE = "sap.ass01.layered.presentation..";
    private static final String SERVICE_LAYER_PACKAGE = "sap.ass01.layered.service..";

    JavaClasses importedClasses = new ClassFileImporter().importPackages("sap.ass01.layered");

    @ArchTest
    public static final ArchRule persistence_layer_should_not_depend_on_presentation =
            noClasses()
                    .that()
                    .resideInAPackage(PERSISTENCE_LAYER_PACKAGE)
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage(PRESENTATION_LAYER_PACKAGE);
    @ArchTest
    public static final ArchRule persistence_layer_should_not_depend_on_service =
            noClasses()
                    .that()
                    .resideInAPackage(PERSISTENCE_LAYER_PACKAGE)
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage(SERVICE_LAYER_PACKAGE);

    @ArchTest
    public static final ArchRule business_layer_should_not_depend_on_presentation =
            noClasses()
                    .that()
                    .resideInAPackage(BUSINESS_LAYER_PACKAGE)
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage(PRESENTATION_LAYER_PACKAGE);

    @ArchTest
    public static final ArchRule business_layer_should_not_depend_on_service =
            noClasses()
                    .that()
                    .resideInAPackage(BUSINESS_LAYER_PACKAGE)
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage(SERVICE_LAYER_PACKAGE);

}
