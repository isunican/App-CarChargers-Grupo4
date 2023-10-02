package es.unican.carchargers.constants;

import java.util.List;

import es.unican.carchargers.R;

/**
 * Static list of charging stations operators. Each operator includes its OpenChargeMap id
 * and the link to the logo resource id.
 */
public enum EOperator {
    WENEA(3371, R.drawable.wenea),
    ZUNDER(3324, R.drawable.zunder),
    REPSOL(91, R.drawable.repsol),
    TESLA_EXCL(23, R.drawable.tesla),   // tesla exclusive (only for teslas)
    TESLA(3534, R.drawable.tesla),      // also for non-teslas
    IONITY(3299, R.drawable.ionity),
    IBERDROLA(2247, R.drawable.iberdrola),
    GENERIC(-1, R.drawable.generic);

    public final static List<Integer> ALL = null;

    /** logo resource id */
    public final int logo;

    /** operator OCM id */
    public final int id;

    private EOperator(int id, int logo) {
        this.id = id;
        this.logo = logo;
    }

    public static EOperator fromId(int id) {
        for (EOperator operator : EOperator.values()) {
            if (id == operator.id) {
                return operator;
            }
        }
        return GENERIC;
    }
}
