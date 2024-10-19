cmake_minimum_required(VERSION 3.19)

project(simple_without_sqlite3_android)

include(FetchContent)

FetchContent_Declare(simple_native
    GIT_REPOSITORY https://github.com/SageMik/sqlite3_simple
    GIT_TAG 007b99dc97e7300dbb1548fb9faebddce1138e80
)
FetchContent_MakeAvailable(simple_native)
