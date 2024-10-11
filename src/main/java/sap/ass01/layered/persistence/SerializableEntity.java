package sap.ass01.layered.persistence;

import java.io.Serializable;

public interface SerializableEntity extends Serializable {
    String getSerializedObject();
}
