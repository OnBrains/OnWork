package org.onbrains.onwork.util;

import org.onbrains.onwork.inf.modelbase.Identifiable;

/**
 * Интерфейс, который вводит общую структуру для модели диалога создания и редактирования некоторого
 * {@link Identifiable}
 *
 * @author Oleg Naumov
 */
public interface EditDialogController<T extends Identifiable> {

	String CREATION_HEADER = "Создание нового объекта";
	String EDITABLE_HEADER = "Редактирование объекта";
	String ADD_LABEL = "Создать";
	String CHANGE_LABEL = "Изменить";

	/**
	 * Определяет режим работы диалога.
	 *
	 * @return <b>true</b> если режим редактирования ({@linkplain #getEditableObject() есть редактируемые объект})
	 */
	default boolean isEditMode() {
		return getEditableObject() != null;
	}

	/**
	 * Реализация метода должна создавать новый объект или изменять существующий в зависимости от
	 */
	void submit(String dlgId);

	void cancel();

	default String getSubmitButtonLabel() {
		return !isEditMode() ? ADD_LABEL : CHANGE_LABEL;
	}

	/**
	 * @return Заголовок для диалога.
	 */
	default String getHeader() {
		return !isEditMode() ? CREATION_HEADER : EDITABLE_HEADER;
	}

	/**
	 * @return Объект, для которого открыт диалог редактирования.
	 */
	T getEditableObject();

	void setEditableObject(T editableObject);

	/**
	 * @return callback, который нужно выполнить после создания/изменения объекта.
	 */
	Callback<T> getCallback();

	void setCallback(Callback<T> callback);

}