package es.unican.carchargers.activities.main;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static es.unican.carchargers.utils.Matchers.isNotEmpty;

import android.content.Context;

import androidx.test.espresso.DataInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import dagger.hilt.android.testing.BindValue;
import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import dagger.hilt.android.testing.UninstallModules;
import es.unican.carchargers.R;
import es.unican.carchargers.repository.IRepository;
import es.unican.carchargers.repository.Repositories;
import es.unican.carchargers.common.RepositoriesModule;
import es.unican.carchargers.utils.HTTPIdlingResource;

/**
 * Example UI Test using Hilt dependency injection
 * Documentation: https://developer.android.com/training/dependency-injection/hilt-testing
 * This test also uses an HTTP Idling Resource
 */
@HiltAndroidTest
@UninstallModules(RepositoriesModule.class)
public class ShowChargersUITest {

    @Rule(order = 0)  // the Hilt rule must execute first
    public HiltAndroidRule hiltRule = new HiltAndroidRule(this);

    @Rule(order = 1)
    public ActivityScenarioRule<MainView> activityRule = new ActivityScenarioRule(MainView.class);

    // necesito el context para acceder a recursos, por ejemplo un json con datos fake
    Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

    @BeforeClass
    public static void setupClass() {
        // si usamos un repository fake que realmente no accede por HTTP, no necesitamos
        // activar este Idling Resource. Lo dejo para tener una referencia.
        HTTPIdlingResource.getInstance().init();
    }

    @AfterClass
    public static void teardownClass() {
        HTTPIdlingResource.getInstance().finish();
    }

    // inject a fake repository that loads the data from a local json file
    // IMPORTANT: all the tests in this class must use this repository
    @BindValue IRepository repository = Repositories
            .getFake(context.getResources().openRawResource(R.raw.chargers_es_100));

    @Test
    public void onSortingClickedTest() {
        onView(withId(R.id.lvChargers)).check(matches(isNotEmpty()));

        onView(withId(R.id.chipPrecio)).perform(click());

        DataInteraction interaction1a = onData(anything()).inAdapterView(withId(R.id.lvChargers)).atPosition(0);
        DataInteraction interaction1b = onData(anything()).inAdapterView(withId(R.id.lvChargers)).atPosition(1);
        DataInteraction interaction1c = onData(anything()).inAdapterView(withId(R.id.lvChargers)).atPosition(2);

        interaction1a.onChildView(withId(R.id.tvTitle)).check(matches(withText("Zunder")));
        interaction1a.onChildView(withId(R.id.tvAddress)).check(matches(withText("Balsicas - Centro Cívico ( Zunder) (Región de Murcia)")));
        interaction1b.onChildView(withId(R.id.tvTitle)).check(matches(withText("Zunder")));
        interaction1b.onChildView(withId(R.id.tvAddress)).check(matches(withText("Torre-Pacheco Club de Golf (Zunder) (Región de Murcia)")));
        interaction1c.onChildView(withId(R.id.tvTitle)).check(matches(withText("Iberdrola")));
        interaction1c.onChildView(withId(R.id.tvAddress)).check(matches(withText("Hotel Mirador de Azuaga (Extremadura)")));

        onView(withId(R.id.chipPotencia)).perform(click());

        DataInteraction interaction2a = onData(anything()).inAdapterView(withId(R.id.lvChargers)).atPosition(0);
        DataInteraction interaction2b = onData(anything()).inAdapterView(withId(R.id.lvChargers)).atPosition(1);
        DataInteraction interaction2c = onData(anything()).inAdapterView(withId(R.id.lvChargers)).atPosition(2);

        interaction2a.onChildView(withId(R.id.tvTitle)).check(matches(withText("(Business Owner at Location)")));
        interaction2a.onChildView(withId(R.id.tvAddress)).check(matches(withText("Hotel Abad Toledo (Castilla-La Mancha)")));
        interaction2b.onChildView(withId(R.id.tvTitle)).check(matches(withText("Mercadona")));
        interaction2b.onChildView(withId(R.id.tvAddress)).check(matches(withText("Mercadona General Villalba (Castilla-La Mancha)")));
        interaction2c.onChildView(withId(R.id.tvTitle)).check(matches(withText("Fenie Energía (Spain)")));
        interaction2c.onChildView(withId(R.id.tvAddress)).check(matches(withText("Parking Indigo (Fenie) (Castilla-La Mancha)")));

        onView(withId(R.id.chipDistancia)).perform(click());
        DataInteraction interaction3a = onData(anything()).inAdapterView(withId(R.id.lvChargers)).atPosition(0);
        DataInteraction interaction3b = onData(anything()).inAdapterView(withId(R.id.lvChargers)).atPosition(1);
        DataInteraction interaction3c = onData(anything()).inAdapterView(withId(R.id.lvChargers)).atPosition(2);

        interaction3a.onChildView(withId(R.id.tvTitle)).check(matches(withText("Wenea")));
        interaction3a.onChildView(withId(R.id.tvAddress)).check(matches(withText("Rest. El Chuletero (Castilla-La Mancha)")));
        interaction3b.onChildView(withId(R.id.tvTitle)).check(matches(withText("Mercadona")));
        interaction3b.onChildView(withId(R.id.tvAddress)).check(matches(withText("Mercadona General Villalba (Castilla-La Mancha)")));
        interaction3c.onChildView(withId(R.id.tvTitle)).check(matches(withText("(Business Owner at Location)")));
        interaction3c.onChildView(withId(R.id.tvAddress)).check(matches(withText("Hotel Abad Toledo (Castilla-La Mancha)")));
    }
}
