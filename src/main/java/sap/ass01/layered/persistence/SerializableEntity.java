package sap.ass01.layered.persistence;

import java.io.Serializable;

/**
 * A serializable entity in the business domain.
 * @param <O> the serialized value type of this object.
 */
public interface SerializableEntity<O> extends Serializable {
    O getSerializedObject();
}
