package com.kuki.data.datasource.local

import com.kuki.data.datasource.dto.contact.ContactsDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

class ContactsLocalDataSource @Inject constructor() {

    private val names = listOf(
        "Александр", "Мария", "Иван", "Ольга", "Дмитрий", "Анна",
        "Сергей", "Екатерина", "Андрей", "Наталья", "Михаил", "Елена",
        "Алексей", "Татьяна", "Артем", "Юлия", "Николай", "Анастасия",
        "Владимир", "Ирина", "Павел", "Светлана", "Константин", "Виктория",
        "Егор", "Ксения", "Станислав", "Алина", "Роман", "Дарья"
    )

    private val surnames = listOf(
        "Иванов", "Смирнов", "Кузнецов", "Попов", "Васильев",
        "Петров", "Соколов", "Михайлов", "Новиков", "Фёдоров",
        "Морозов", "Волков", "Алексеев", "Лебедев", "Семёнов",
        "Егоров", "Павлов", "Козлов", "Степанов", "Николаев",
        "Орлов", "Андреев", "Макаров", "Никитин", "Захаров",
        "Зайцев", "Соловьёв", "Борисов", "Яковлев", "Григорьев"
    )

    private val phoneCode = listOf("29", "44")

    private val avatars = listOf(
        "https://masterpiecer-images.s3.yandex.net/5fd531dca6427c7:upscaled",
        "https://711515.selcdn.ru/recycleCDN/main/9/9ead29f7ebb448d64cd47e567738b77a_thumbnail.jpg",
        "https://zefirka.net/wp-content/uploads/2024/10/milye-kotiki-chya-nezhnost-sposobna-rastopit-samye-xolodnye-serdca-1.jpg",
        "https://img.freepik.com/free-photo/view-beautiful-persian-domestic-cat_23-2151773821.jpg?semt=ais_hybrid&w=740",
        "https://kartinki.pics/uploads/posts/2022-03/1646793820_41-kartinkin-net-p-milii-kotik-kartinki-44.jpg",
        "https://kartinki.pics/uploads/posts/2022-02/1645199493_1-kartinkin-net-p-milie-kartinki-kotikov-2.jpg",
        "https://gazeta-rybinsk.ru/wp-content/uploads/2019/12/koti1-e1672580602262.jpg",
        "https://zoographia.ru/upload/iblock/f5e/h6up57sp4z07z1nsdx8cq5e5514m4bwv.jpg",
        "https://vot-enot.com/wp-content/uploads/2021/11/94895c23dcc1cf7.jpg.webp",
        "https://vot-enot.com/wp-content/uploads/2021/11/pasted-image-0-1024x1024.jpg.webp",
        "https://zoo-club.com.ua/image/cache/catalog/blog/news003-910x605.jpg",
        "https://s0.rbk.ru/v6_top_pics/media/img/0/61/346832026749610.webp",
        "https://натуралка.рф/files/products/sobaka-drug-cheloveka-2013.800x600w.jpg?216711c97607dc376a53a5b16fe2b55d",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTzP5q1R2snoJsMefkFKbgI95MHc4WLeiegYRuLNUETAS5MVBXhtJLTWu_kxN2EZHFjef8&usqp=CAU",
        "https://img51994.kanal-o.ru/img/2020-06-29/fmt_81_24_shutterstock_547953349.jpg",
    )

    private var contacts = emptyList<ContactsDto>()

    suspend fun fetchContacts(): List<ContactsDto> = withContext(Dispatchers.IO) {
        contacts
            .takeIf { it.isEmpty() }
            ?.let {
                contacts = List(100) { index ->
                    val name = names.random()
                    val surname = createSurname(name)
                    ContactsDto(
                        id = index.toString(),
                        name = name,
                        surname = surname,
                        phoneNumber = createPhoneNumber(),
                        avatarUrl = avatars.random()
                    )
                }
                contacts
            }
            ?: contacts
    }

    private fun createPhoneNumber(): String {
        return "+375 (${phoneCode.random()}) ${Random.nextInt(1000)}-${Random.nextInt(100)}-${
            Random.nextInt(
                100
            )
        }"
    }

    private fun createSurname(name: String): String {
        return if (name.endsWith("а") || name.endsWith("я")) {
            // Женские фамилии (добавляем -а/-я)
            surnames.random().let {
                if (it.endsWith("в")) it.dropLast(1) + "ва"
                else it + "а"
            }
        } else {
            // Мужские фамилии (без изменений)
            surnames.random()
        }
    }

}