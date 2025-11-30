package com.kuki.testing.repository

import com.kuki.domain.entry.contact.ContactEntry
import com.kuki.domain.repository.ContactsRepository
import javax.inject.Inject

private val contact1 = ContactEntry(
    id = "0",
    name = "Василий",
    surname = "Пупкин",
    phoneNumber = "+375 (29) 123-45-67",
    avatarUrl = "https://masterpiecer-images.s3.yandex.net/5fd531dca6427c7:upscaled"
)

private val contact2 = ContactEntry(
    id = "1",
    name = "Сергей",
    surname = "Кузнецов",
    phoneNumber = "+375 (29) 133-45-11",
    avatarUrl = "https://..."
)

private val contact3 = ContactEntry(
    id = "2",
    name = "Миша",
    surname = "Гаврилов",
    phoneNumber = "+375 (29) 563-45-99",
    avatarUrl = "https://..."
)

private val contact4 = ContactEntry(
    id = "3",
    name = "Анна",
    surname = "Петрова",
    phoneNumber = "+375 (29) 345-67-89",
    avatarUrl = "https://example.com/avatars/anna.jpg"
)

private val contact5 = ContactEntry(
    id = "4",
    name = "Дмитрий",
    surname = "Сидоров",
    phoneNumber = "+375 (33) 123-45-67",
    avatarUrl = "https://example.com/avatars/dmitry.jpg"
)

private val contact6 = ContactEntry(
    id = "5",
    name = "Екатерина",
    surname = "Иванова",
    phoneNumber = "+375 (44) 987-65-43",
    avatarUrl = "https://example.com/avatars/ekaterina.jpg"
)

private val contact7 = ContactEntry(
    id = "6",
    name = "Алексей",
    surname = "Козлов",
    phoneNumber = "+375 (25) 555-44-33",
    avatarUrl = "https://example.com/avatars/aleksey.jpg"
)

private val contact8 = ContactEntry(
    id = "7",
    name = "Ольга",
    surname = "Новикова",
    phoneNumber = "+375 (29) 777-88-99",
    avatarUrl = "https://example.com/avatars/olga.jpg"
)

private val contact9 = ContactEntry(
    id = "8",
    name = "Сергей",
    surname = "Васильев",
    phoneNumber = "+375 (33) 222-11-00",
    avatarUrl = "https://example.com/avatars/sergey.jpg"
)

private val contact10 = ContactEntry(
    id = "9",
    name = "Мария",
    surname = "Федорова",
    phoneNumber = "+375 (44) 333-22-11",
    avatarUrl = "https://example.com/avatars/maria.jpg"
)

private val contact11 = ContactEntry(
    id = "10",
    name = "Иван",
    surname = "Павлов",
    phoneNumber = "+375 (25) 444-55-66",
    avatarUrl = "https://example.com/avatars/ivan.jpg"
)

private val contact12 = ContactEntry(
    id = "11",
    name = "Наталья",
    surname = "Семенова",
    phoneNumber = "+375 (29) 666-77-88",
    avatarUrl = "https://example.com/avatars/natalya.jpg"
)

private val contact13 = ContactEntry(
    id = "12",
    name = "Андрей",
    surname = "Громов",
    phoneNumber = "+375 (33) 888-99-00",
    avatarUrl = "https://example.com/avatars/andrey.jpg"
)

private val contact14 = ContactEntry(
    id = "13",
    name = "Татьяна",
    surname = "Орлова",
    phoneNumber = "+375 (44) 111-22-33",
    avatarUrl = "https://example.com/avatars/tatyana.jpg"
)

private val contact15 = ContactEntry(
    id = "14",
    name = "Павел",
    surname = "Волков",
    phoneNumber = "+375 (25) 999-88-77",
    avatarUrl = "https://example.com/avatars/pavel.jpg"
)

private val contact16 = ContactEntry(
    id = "15",
    name = "Юлия",
    surname = "Лебедева",
    phoneNumber = "+375 (29) 123-45-67",
    avatarUrl = "https://example.com/avatars/yulia.jpg"
)

private val contact17 = ContactEntry(
    id = "16",
    name = "Роман",
    surname = "Соловьев",
    phoneNumber = "+375 (33) 234-56-78",
    avatarUrl = "https://example.com/avatars/roman.jpg"
)

private val contact18 = ContactEntry(
    id = "17",
    name = "Елена",
    surname = "Кузнецова",
    phoneNumber = "+375 (44) 345-67-89",
    avatarUrl = "https://example.com/avatars/elena.jpg"
)

private val contact19 = ContactEntry(
    id = "18",
    name = "Виктор",
    surname = "Попов",
    phoneNumber = "+375 (25) 456-78-90",
    avatarUrl = "https://example.com/avatars/viktor.jpg"
)

private val contact20 = ContactEntry(
    id = "19",
    name = "Светлана",
    surname = "Андреева",
    phoneNumber = "+375 (29) 567-89-01",
    avatarUrl = "https://example.com/avatars/svetlana.jpg"
)

internal class TestContactsRepository @Inject constructor() : ContactsRepository {

    override suspend fun fetchContacts(): List<ContactEntry> {
        return listOf(
            contact1,
            contact2,
            contact3,
            contact4,
            contact5,
            contact6,
            contact7,
            contact8,
            contact9,
            contact10,
            contact11,
            contact12,
            contact13,
            contact14,
            contact15,
            contact16,
            contact17,
            contact18,
            contact19,
            contact20
        )
    }
}