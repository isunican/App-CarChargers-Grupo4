package es.unican.carchargers.activities.main;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.CoreMatchers.anyOf;
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
import es.unican.carchargers.common.RepositoriesModule;
import es.unican.carchargers.repository.IRepository;
import es.unican.carchargers.repository.Repositories;
import es.unican.carchargers.utils.HTTPIdlingResource;
import static org.hamcrest.Matchers.not;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import android.view.View;
import android.widget.ListView;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


@HiltAndroidTest
@UninstallModules(RepositoriesModule.class)
public class filterChargersUITest {

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
    @BindValue
    IRepository repository = Repositories
            .getFake(context.getResources().openRawResource(R.raw.chargers_es_100));

    @Test
    public void filterChargersTestAllPositions() {
        onView(withId(R.id.lvChargers)).check(matches(isNotEmpty()));

        // Lista de chips para seleccionar
        int[] chips = {R.id.chipTesla, R.id.chipIberdrola, R.id.chipIonity, R.id.chipZunder};

        for (int chip : chips) {
            // Seleccionar el chip
            onView(withId(chip)).perform(click());
        }

        // Deseleccionar un chip y verificar que los elementos que quedan no son de ese tipo
        onView(withId(R.id.chipIberdrola)).perform(click());
        int count = getCountFromListView(R.id.lvChargers);
        for (int i = 0; i < count; i++) {
            onData(anything())
                    .inAdapterView(withId(R.id.lvChargers)).atPosition(i)
                    .onChildView(withId(R.id.tvTitle))
                    .check(matches(not(withText("Zunder"))));
        }
        for (int i = 0; i < count; i++) {
            onData(anything())
                    .inAdapterView(withId(R.id.lvChargers)).atPosition(i)
                    .onChildView(withId(R.id.tvTitle))
                    .check(matches(withText("Iberdrola")));
        }
        for (int i = 0; i < count; i++) {
            onData(anything())
                    .inAdapterView(withId(R.id.lvChargers)).atPosition(i)
                    .onChildView(withId(R.id.tvTitle))
                    .check(matches(not(withText("Ionity"))));
        }
        for (int i = 0; i < count; i++) {
            onData(anything())
                    .inAdapterView(withId(R.id.lvChargers)).atPosition(i)
                    .onChildView(withId(R.id.tvTitle))
                    .check(matches(not(withText("Tesla"))));
        }

        onView(withId(R.id.chipZunder)).perform(click());
        int count1 = getCountFromListView(R.id.lvChargers);
        for (int i = 0; i < count1; i++) {
            onData(anything())
                    .inAdapterView(withId(R.id.lvChargers)).atPosition(i)
                    .onChildView(withId(R.id.tvTitle))
                    .check(matches(anyOf(withText("Zunder"), withText("Iberdrola"))));
        }
        for (int i = 0; i < count1; i++) {
            onData(anything())
                    .inAdapterView(withId(R.id.lvChargers)).atPosition(i)
                    .onChildView(withId(R.id.tvTitle))
                    .check(matches(not(withText("Ionity"))));
        }
        for (int i = 0; i < count1; i++) {
            onData(anything())
                    .inAdapterView(withId(R.id.lvChargers)).atPosition(i)
                    .onChildView(withId(R.id.tvTitle))
                    .check(matches(not(withText("Tesla"))));
        }

    }

    private int getCountFromListView(int listViewId) {
        final int[] count = new int[1];
        onView(withId(listViewId)).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return new TypeSafeMatcher<View>() {
                    @Override
                    protected boolean matchesSafely(View item) {
                        return item instanceof ListView;
                    }

                    @Override
                    public void describeTo(Description description) {
                        description.appendText("is a ListView");
                    }
                };
            }

            @Override
            public String getDescription() {
                return "Get count from ListView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ListView listView = (ListView) view;
                count[0] = listView.getAdapter().getCount();
            }
        });
        return count[0];
    }


}
