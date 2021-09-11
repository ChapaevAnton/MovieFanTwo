# MovieFanTwo
on development

ветки: 
* develop - проверено
* homework33 - на проверке

Навигатор по фильмам и сериалам, рейтинги и рецензии

• Поиск со множеством фильтров;

• Подборки на любой вкус;

• На карточке фильма — трейлер, описание, интересные факты, рейтинг и рецензии;

• На карточке персоны — полная фильмография актёров, режиссёров и т.д.

![image](https://user-images.githubusercontent.com/69672210/132940676-9f2eae9d-2f2a-4f6a-8d2b-57402e76bcd1.png)
![image](https://user-images.githubusercontent.com/69672210/132940682-35ce642b-6bb3-4476-beef-ab5c5efd7553.png)

starting from branch homework32

25.08.21 fix gradle bags data binding is not work

Ater updating the android studio, a failure occurred due to which the project was not going to be assembled and gave an error kotlin-kapt 
and date binding during assembly, it was not possible to fix it after 18 hours. A project with an error up to branch 32 on github. This project 
is rebuilt on the basis of a new project and all files from branch 32 have been transferred to it.

The project is now being developed in this repository. The old project, up to branch 32, is available at this link https://github.com/ChapaevAnton/MovieFan

p\s managed to fix, the problem was that the name of the packages should not start with a capital letter ... it turned out after the transfer(
