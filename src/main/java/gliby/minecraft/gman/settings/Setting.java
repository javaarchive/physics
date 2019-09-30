package gliby.minecraft.gman.settings;

import gliby.minecraft.gman.settings.INIProperties.INIPropertiesReadFailure;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.ArrayList;

/**
 *
 */
public abstract class Setting {

    public final String name, category;
    public final Side side;
    public Object data;
    private boolean hidden;
    private ArrayList<Listener> writeListeners;
    private ArrayList<Listener> readListeners;

    /**
     * @param data
     * @param name
     */
    Setting(String category, String name, Object data, Side side) {
        this.category = category;
        this.name = name;
        this.data = data;
        this.side = side;
        this.readListeners = new ArrayList<Setting.Listener>();
        this.writeListeners = new ArrayList<Setting.Listener>();
    }

    public Object getSettingData() {
        return data;
    }

    public abstract boolean hasChanged();

    public abstract void read(final INIProperties ini) throws INIPropertiesReadFailure;

    public abstract void write(final INIProperties ini);

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " - " + ": " + data;
    }

    public boolean isHidden() {
        return hidden;
    }

    public Setting setHidden(boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    public ArrayList<Listener> getWriteListeners() {
        return writeListeners;
    }

    public Setting addWriteListener(Listener listener) {
        writeListeners.add(listener);
        return this;
    }

    public ArrayList<Listener> getReadListeners() {
        return readListeners;
    }

    public Setting addReadListener(Listener listener) {
        readListeners.add(listener);
        return this;
    }

    public enum Side {
        CLIENT, SERVER, BOTH;

        public static Side getEffectiveSide() {
            return toSide(FMLCommonHandler.instance().getEffectiveSide());
        }

        private static Side toSide(net.minecraftforge.fml.relauncher.Side effectiveSide) {
            return effectiveSide == net.minecraftforge.fml.relauncher.Side.CLIENT ? Side.CLIENT : Side.SERVER;
        }
    }

    public interface Listener {

        void listen(final INIProperties ini);

    }
}
