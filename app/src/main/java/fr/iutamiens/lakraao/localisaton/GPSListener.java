package fr.iutamiens.lakraao.localisaton;

import android.location.Location;

/***
 * Listener de changement de position
 * Created by omer on 28/02/18.
 */
interface GPSListener {
    public void positionChanged(Location location);
}
